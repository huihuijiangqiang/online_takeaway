package web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.ClassUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@WebServlet("/AjaxUploadFile")
public class AjaxUploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AjaxUploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }
    String contextPath;
    String projectUrl="D:/it/bf/15/src/main/webapp/oUser/statci/images";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = getServletContext().getRealPath("/")+ "oUser/statci/images/";
        //借助工具解析
        System.out.println("------------");
        System.out.println(contextPath);
        System.out.println("------------");
        //判断传入的是否是文件类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println(isMultipart);
        if(isMultipart){

            //创建FileItem对象工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //获取临时文件夹
            ServletContext servletContext = this.getServletConfig().getServletContext();
            //获取临时文件夹
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            System.out.println(repository);
            factory.setRepository(repository);
            //创建文件上传处理器
            ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
            //解决中文乱码参数
            upload.setHeaderEncoding("utf-8");
            List<FileItem> items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String fileName = "";

            for(FileItem item:items) {
                if(!item.isFormField()) {
                    fileName=saveFile(item);

                }
            }

            //把图片的路径传给页面
            response.getWriter().write(fileName);
            //把路径传递给数据库

        }
    }
    private String saveFile(FileItem item) {
        //获取上传图片名称
        String fileFullName = item.getName();
        File fileInfo = new File(fileFullName);
        String fileName = new Date().getTime()+fileInfo.getName();
        //获取当前项目的根目录
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();

        //保存路径为当前工程根目录的upload文件夹
        File savePath = new File(contextPath);

        //如果路径不存在，就新建目录
        if(!savePath.exists()) {
           boolean f= savePath.mkdir();
        }

        File uploadedFile = new File(contextPath+File.separator+fileName);
        File uploadedFile1 = new File(projectUrl+File.separator+fileName);
        String oldpath=uploadedFile1.toString();
        String newpath=uploadedFile.toString();
        System.out.println("uploadedFile"+uploadedFile);
        System.out.println("uploadedFile1"+uploadedFile1);
        try {
            //
            item.write(uploadedFile1);//写入


            System.out.println("保存真实文件成功");

            copy(oldpath,newpath);
            return this.getServletContext().getContextPath()+"/oUser/statci/images/"+fileName;


        } catch (Exception e) {
            System.out.println(e);
            // TODO Auto-generated catch block
            System.out.println("保存真实文件失败");
            return "";
        }
    }
    private static void copy(String oldpath, String newpath) throws IOException {
        File oldPath=new File(oldpath);
        File newPath=new File(newpath);
        if(!newPath.exists()){
            Files.copy(oldPath.toPath(),newPath.toPath());
        }else {
            newPath.delete();
            Files.copy(oldPath.toPath(),newPath.toPath());
        }


    }
    private List<FileItem> getRequestFileItems(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
