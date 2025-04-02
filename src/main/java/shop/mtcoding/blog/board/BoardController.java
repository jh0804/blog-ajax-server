package shop.mtcoding.blog.board;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        //클라이언트에서 쿠키 꺼내기
        Cookie cookie = new Cookie("boardId", id + ""); //문자열로 줘야됨 -> String.valueOf(id)도 가능
        cookie.setHttpOnly(true); // JS로 꺼낼 수 없도록 속성 설정 -> 프로토콜의 용도로만 쓰도록 해줌
        response.addCookie(cookie);
        // response 헤더 - Set-Cookie : boardId=1
        // id 클라이언트에게 전달
        request.setAttribute("boardId", id);
        return "board/updateForm";
    }
}
