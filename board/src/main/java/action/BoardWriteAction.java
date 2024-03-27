package action;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardWriteAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        req.setCharacterEncoding("utf-8");

        // qna_board_write.jsp 에서 넘긴 값 가져오기
        BoardDto insertDto = new BoardDto();
        insertDto.setName(req.getParameter("name"));
        insertDto.setTitle(req.getParameter("title"));
        insertDto.setContent(req.getParameter("content"));
        insertDto.setPassword(req.getParameter("password"));

        // 업로드 처리
        Part part = req.getPart("attach");
        String fileName = getFileName(part);

        String saveDir = "c:\\upload";

        if (!fileName.isEmpty()) {
            // universally unique identifier (UUID) : 고유한 값 생성
            // 파일명이 중복되면 저장되지 않으니 서버에 저장할 땐 다른 파일명을 사용
            UUID uuid = UUID.randomUUID();
            // 고유값_사용자가올린파일명.파일형식
            File uploadFile = new File(saveDir + File.separator + uuid + "_" + fileName);

            // c:\\upload\\1.jpg
            // part.write(saveDir + "\\" + fileName); // 서버 디스크에 파일이 저장
            // uuid 를 설정하지 않고 업로드한 파일 이름이 중복되면 덮어쓰기 됨

            part.write(uploadFile.toString());
            insertDto.setAttach(uploadFile.getName());
        }

        System.out.println(insertDto);

        // 서비스 호출
        BoardService service = new BoardServiceImpl();
        if (!service.insert(insertDto)) {
            path = "/view/qna_board_write.jsp";
        }
        return new ActionForward(path, true);
    }

    private String getFileName(Part part) {
        // 웹페이지의 header에 담긴 content-disposition 속성의 값을 가져오기
        // content-disposition 은 파일 형태의 데이터가 있을 때 header에 담기는 속성
        // Content-Disposition: attachment; filename="filename.jpg"
        String header = part.getHeader("content-disposition");
        String[] arr = header.split(";");
        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];
            // 가지고 온 파일 중에 이름이 filename으로 시작하는 파일 찾기
            if (temp.trim().startsWith("filename")) {
                // 파일 이름 돌려받기
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }

        return "";
    }
}
