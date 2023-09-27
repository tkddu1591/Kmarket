package kr.co.kmarket.service;

public class PageService {
    private static final PageService INSTANCE = new PageService();
    private PageService() {}
    public static PageService getInstance() {
        return INSTANCE;
    }
    public int getLastPageNum(int total) {

        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else{
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    // 페이지 그룹
    public int[] getPageGroupNum(int currentPage, int lastPageNum) {
        int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
        int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
        int pageGroupEnd = currentPageGroup * 10;

        if(pageGroupEnd > lastPageNum){
            pageGroupEnd = lastPageNum;
        }

        int[] result = {pageGroupStart, pageGroupEnd};

        return result;
    }

    // 페이지 시작번호
    public int getPageStartNum(int total, int currentPage) {
        int start = (currentPage - 1) * 10;
        return total - start;
    }

    // 현재 페이지 번호
    public int getCurrentPage(String pg) {
        int currentPage = 1;

        if(pg != null || pg==""){
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    // Limit 시작번호
    public int getStartNum(int currentPage) {
        return (currentPage - 1) * 10;
    }


    public int getLastPageNumReview(int total) {

        int lastPageNum = 0;

        if(total % 5 == 0){
            lastPageNum = total / 5;
        }else{
            lastPageNum = total / 5 + 1;
        }

        return lastPageNum;
    }

    // 페이지 그룹
    public int[] getPageGroupNumReview(int currentPage, int lastPageNum) {
        int currentPageGroup = (int)Math.ceil(currentPage / 5.0);
        int pageGroupStart = (currentPageGroup - 1) * 5 + 1;
        int pageGroupEnd = currentPageGroup * 5;

        if(pageGroupEnd > lastPageNum){
            pageGroupEnd = lastPageNum;
        }

        int[] result = {pageGroupStart, pageGroupEnd};

        return result;
    }

    // 페이지 시작번호
    public int getPageStartNumReview(int total, int currentPage) {
        int start = (currentPage - 1) * 5;
        return total - start;
    }

    // 현재 페이지 번호
    public int getCurrentPageReview(String pg) {
        int currentPage = 1;

        if(pg != null || pg==""){
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    // Limit 시작번호
    public int getStartNumReview(int currentPage) {
        return (currentPage - 1) * 5;
    }
}
