<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.BufferedOutputStream" %>
<%@ page import="java.io.FileInputStream" %>

<%
        // 클라이언트가 요청한 fileName 가져오기
        String fileName = request.getParameter("fileName"); // 5338bd6d-a25e-47e0-b693-fc0869660472_dish1.jpg

        // 서버에 저장된 폴더 경로 지정
        String fDownloadPath = "c:\\upload";
        String filePath = fDownloadPath + "\\" + fileName; // c:\\upload\\5338bd6d-a25e-47e0-b693-fc0869660472_dish1.jpg

        // 하드디스크에 있는 파일 읽어오기 위한 개체 생성
        FileInputStream in = new FileInputStream(filePath);

        // 브라우저가 응답할 때 읽어온 파일 보내기
        response.setContentType("application/octet-stream");
        // 한글로 된 파일의 경우 디스크에 저장되면서 깨졌을 수도 있어서 변경
        fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");

        int start = fileName.lastIndexOf("_");
        String oriName = fileName.substring(start + 1);

        // response header - Content-Disposition: attachment - filename=dish1.jpg
        response.setHeader("Content-Disposition", "attachment;filename=" + oriName);

        out.clear();
        out = pageContext.pushBody();

        BufferedOutputStream buf = new BufferedOutputStream(response.getOutputStream());

        int numRead;
        byte b[] = new byte[4096];
        while ((numRead = in.read(b, 0, b.length)) != -1) {
            buf.write(b, 0, numRead);
        }
        buf.flush();
        buf.close();
        in.close();
 %>