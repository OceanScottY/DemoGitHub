package ExcelOperate;

import Utils.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Scott on 2019/1/7
 */
public class ExcelUtiles {

    private POIFSFileSystem fs;
    private HSSFWorkbook wb;   //读取.xls和写入excle文件。
    private HSSFSheet sheet;
    private HSSFRow row;


    /**
     * 读取Excel表格表头的内容
     * @param is
     * @return      表头内容的数组
     */
    public String[] readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            //title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @param is
     * @return  Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, String> readExcelContent(InputStream is) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            colNum = row.getPhysicalNumberOfCells();
            int j = 0;
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                str += getCellFormatValue(row.getCell((short) j)).trim() + "|";
                j++;
            }
            content.put(i, str);
            str = "";
        }
        return content;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                //strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getStringCellValue();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    public static Map<Integer, String> getExcelValue( String path) throws FileNotFoundException{

        ExcelUtiles excelReader = new ExcelUtiles();
        // 对读取Excel表格内容测试
        InputStream is2 = new FileInputStream(path);
        Map<Integer, String> map = excelReader.readExcelContent(is2);

        return map;

    }


    /**
     * 将excel中某个sheet也的值和Bean的属性名放入map中, excel文件可以是xls或者xlsx文件
     * @param localApplyPath
     * 			excel文件路径,带路径和文件名
     * @param sheetName
     * 			表格中sheet页的名字
     * @param clazz
     * 			bean中类的class
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> parseExcel(String localApplyPath, String sheetName, Class clazz) throws Exception{


        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        FileInputStream input= new FileInputStream(localApplyPath);
        Workbook workbook = WorkbookFactory.create(input);
//		if(localApplyPath.endsWith(xls)){
//			System.out.println("xls文件");
//			workbook = new HSSFWorkbook(input);
//		}else if(localApplyPath.endsWith(xlsx)){
//			System.out.println("xlsx文件");
//			workbook = WorkbookFactory.create(input);
//		}
        //获取Excel表中指定名称的sheet页
        Sheet sheet = workbook.getSheet(sheetName);
        int excleRowNum = sheet.getLastRowNum() + 1;//获取总行数
        int excleColNum = sheet.getRow(1).getPhysicalNumberOfCells();//总列数
        String [] columName = new String[excleColNum];//相应的javabean类的属性名称数组
        Field[] fields = clazz.getDeclaredFields();
        for(int j=1; j<excleRowNum; j++){
            Row row = (Row) sheet.getRow(j);
            Map<String,Object> resultMap = new HashMap<String, Object>();
            for(int i=0; i<excleColNum; i++){
                //取出当前的cell的值和javabean类的属性  放入map中

//				String VVV = getStringCellValue(row.getCell(i));
                Cell cell = row.getCell(i);
                resultMap.put(fields[i].getName(), getStringValue(cell));

            }
            resultList.add(resultMap);
        }

        return resultList;
    }

    /**
     * 往excel中某个sheet页某一列的内容加一个前缀
     * @param localApplyPath
     * 			excel文件路径,带路径和文件名
     * @param sheetName
     * 			表格中sheet页的名字
     * @param clazz
     * 			bean中类的class
     * @param attribute
     * 			sheet页中对应bean的属性,如果为空串,则将该值置为空
     * @param pre
     * 			所加的前缀
     */
    public Boolean changeExcel(String localApplyPath, String sheetName,  Class clazz, String attribute, String pre){

        FileInputStream input = null;
        FileOutputStream out = null;
        String cellValue = "";
        try {
            input = new FileInputStream(localApplyPath);

            Workbook workbook = WorkbookFactory.create(input);
            //获取Excel表中指定名称的sheet页
            Sheet sheet = workbook.getSheet(sheetName);
            int excleRowNum = sheet.getLastRowNum() + 1;//获取总行数
            int excleColNum = sheet.getRow(1).getPhysicalNumberOfCells();//总列数
            String [] columName = new String[excleColNum];//相应的javabean类的属性名称数组
            Field[] fields = clazz.getDeclaredFields();
            int index = 0;
            int taskExeType_index = fields.length + 20;
            String taskExeType_value = "0";
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if("taskExeType".equals(fieldName)){
                    taskExeType_index = i;
                }
                if(fields[i].getName().equals(attribute)){
                    index = i;
                }

            }
            if(index == 0){
                return false;
            }
            for(int j=1; j<excleRowNum; j++){
                Row row = (Row) sheet.getRow(j);
                //取出当前的cell的值和javabean类的属性
                Cell cell = row.getCell(index);
                cellValue = getStringValue(cell);
                //如果cell中的内容中没有前缀,则加上前缀
                if(!cellValue.contains(pre)){
                    cellValue = pre + getStringValue(cell);
                }
                //修改任务的执行方式为立即执行
                if(taskExeType_index < excleColNum){
                    Cell cell_taskExeType = row.getCell(taskExeType_index);
                    cell_taskExeType.setCellValue(taskExeType_value);
                }
                if("".equals(pre)){
                    cell.setCellValue("");
                }else {
                    cell.setCellValue(cellValue);
                }
            }
            out = new FileOutputStream(localApplyPath);
            workbook.write(out);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try{
                input.close();
                out.close();
            }catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return true;
    }


    private String getStringValue(Cell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                //strCell = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }


    /**
     * 利用反射将Map中的数据生成相应的T对象
     * @param map
     * @param clazz
     * @return T对象
     * @throws Exception
     */
    public  <T>T toObject(Map map, Class clazz) throws Exception{
        T obj = (T) clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m:methods){
            if(m.getName().startsWith("set")){//找到set方法
                String str = m.getName().substring(3);//该set方法对应的属性名 首字母为大写
                String attribute = StringUtil.toLowerFirstOne(str);
                String value = (String)map.get(attribute);
                if(value != null){
                    m.invoke(obj, value);
                }
            }
        }
        return obj;
    }

    public String getExcel(String filePath){
        String fileToBeRead = "D:\\test.xlsx";
        Workbook workbook;
        String value = "";
        try {
            if(fileToBeRead.indexOf(".xlsx")>-1){
                workbook = new XSSFWorkbook(new FileInputStream(filePath));
            } else {
                workbook = new HSSFWorkbook(new FileInputStream(filePath));
            }
            //HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead)); //2003 创建对Excel工作簿文件的引用
            //XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileToBeRead)); //2007,2010 创建对Excel工作簿文件的引用
            Sheet sheet = workbook.getSheet("Sheet1"); // 创建对工作表的引用
            int rows = sheet.getPhysicalNumberOfRows();// 获取表格的
            int columns = 0;
            for (int r = 0; r < rows; r++) { // 循环遍历表格的行
                if(r==0){
                    //在第一行标题行计算出列宽度,因为数据行中可能会有空值
                    columns = sheet.getRow(r).getLastCellNum();
                    continue;
                }

                Row row = sheet.getRow(r); // 获取单元格中指定的行对象
                if (row != null) {
                    //int cells = row.getPhysicalNumberOfCells();// 获取一行中的单元格数

                    //int cells = row.getLastCellNum();// 获取一行中最后单元格的编号（从1开始）
                    for (short c = 0; c < columns; c++) { // 循环遍历行中的单元格
                        Cell cell = row.getCell((short) c);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) { // 判断单元格的值是否为字符串类型
                                value += cell.getStringCellValue() + ",";
                            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) { // 判断单元格的值是否为数字类型
                                //if(DateUtil.isCellDateFormatted(cell)){

                                //  cell.getDateCellValue();
                                //日期型
                                //}
                                value += cell.getNumericCellValue() + ",";
                            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) { // 判断单元格的值是否为布尔类型
                                value += cell.getStringCellValue() + ",";
                            }
                        }
                    }
                }
                String[] str = value.split(",");
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
}


    public static void main(String[] args) {
        String filePath = "e:\\test.xls";
        try {
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream(filePath);
            ExcelUtiles excelReader = new ExcelUtiles();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }
            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream(filePath);
            Map<Integer, String> map = excelReader.readExcelContent(is2);
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }



}
