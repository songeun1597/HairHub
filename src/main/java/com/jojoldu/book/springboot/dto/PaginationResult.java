package com.jojoldu.book.springboot.dto;

import lombok.Getter;

@Getter
public class PaginationResult {

    private int currentPageNo;
    private int totalPageCount;
    private int firstPageNoOnPageList;
    private int lastPageNoOnPageList;
    private int firstRecordIndex;
    private int lastRecordIndex;

    public PaginationResult(int currentPageNo, int totalPageCount, int firstPageNoOnPageList, int lastPageNoOnPageList, int firstRecordIndex, int lastRecordIndex) {
        this.currentPageNo = currentPageNo;
        this.totalPageCount = totalPageCount;
        this.firstPageNoOnPageList = firstPageNoOnPageList;
        this.lastPageNoOnPageList = lastPageNoOnPageList;
        this.firstRecordIndex = firstRecordIndex;
        this.lastRecordIndex = lastRecordIndex;
    }

}
