package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private SearchDto searchDto;
    private int total;

    public PageDto(SearchDto searchDto, int total) {
        this.searchDto = searchDto;
        this.total = total;

        endPage = (int) (Math.ceil(searchDto.getPage() / 10.0)) * 10;
        // 사용자가 페이지 3 을 요청하면 endPage == 10
        // 13 페이지를 요청하면 endPage == 20
        startPage = endPage - 9; // 3페이지 요청 시 1, 13페이지 요청 시 11

        int realEnd = (int) (Math.ceil((total / 1.0) / searchDto.getAmount()));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
