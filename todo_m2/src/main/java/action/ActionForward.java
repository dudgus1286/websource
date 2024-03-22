package action;

public class ActionForward {
    private String path; // 경로를 지정하는 변수
    private boolean redirect; // sendRedirect 여부(true)

    public String getPath() {
        return path;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public ActionForward() {
    }

    public ActionForward(String path, boolean redirect) {
        this.path = path;
        this.redirect = redirect;
    }
}
