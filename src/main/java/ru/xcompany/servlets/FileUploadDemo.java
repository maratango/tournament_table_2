package ru.xcompany.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.xcompany.entities.DBmaker;
import ru.xcompany.entities.fileReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class FileUploadDemo extends HttpServlet {

    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;

    private String filePath = "C:/TestProjects/tournament_table_2/src/main/resources/uploads/";
    private File file;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from FileUploadDemo");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewlar/fileupload.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //кодировка запроса и ответа
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //сообщаем тип ответа
        resp.setContentType("text/html");

        //получаем объект "писальщик"
        PrintWriter writer = resp.getWriter();

        //создаём объекты для дальнейшей магии
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        //магия с згрузкой файла
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                    //создать атрибут fileName запроса и записать в него значение - имя загруженного файла
                    req.setAttribute("fileName", fileName);         //    !!!!!!!!!!!

                    //чтение загруженно файла и запись в бд значений из файла
                    new fileReader().readFileWriteBD(fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //создание записи в таблице Матч
//        new DBmaker().addRecord();

        //передаём управление с наполненным запросом и ответом в метод, для вывода вьюхи
        doGet(req, resp);

    }

}
