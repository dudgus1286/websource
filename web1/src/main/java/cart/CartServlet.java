package cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addCart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리
        req.setCharacterEncoding("utf-8");

        // 사용자가 선택한 값 가져오기
        String product = req.getParameter("product");

        // 세션 담기
        HttpSession session = req.getSession();
        // 1) 장바구니 세션 존재 확인
        ArrayList<String> products = (ArrayList<String>) session.getAttribute("products");
        // 2) 없을경우 새 세션 생성 , 있을 경우 기존 세션에 추가
        if (products == null) {
            products = new ArrayList<>();
            products.add(product);
            session.setAttribute("products", products);
        } else {
            products.add(product);
        }

        // session.setAttribute("product", product); - 값을 하나로 한정할 때 (목록아님)

        // 페이지 이동 - basket.jsp
        resp.sendRedirect("/cart/basket.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
