package com.homeprojs.imagesearchabstractionlayer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Pagination<T> {
    private final List<T> srcLs;
    private final int PAGE_SIZE;
    private final List<ArrayList<T>> lsOfPaged = new ArrayList<>();

    public Pagination(List<T> ls, int pageSize) {
        super();
        this.srcLs = ls;
        this.PAGE_SIZE = pageSize;
        divideIntoPages();
    }

    private void divideIntoPages() {
        int totalPageNum;
        if (this.srcLs.size() % PAGE_SIZE == 0) {
            totalPageNum = this.srcLs.size() / PAGE_SIZE;
            ArrayList<T> page = new ArrayList<>(PAGE_SIZE);
            // array has exactly a divisible num of elements
            for (int i = 0; i < totalPageNum; i++) {
                for (int j = i * PAGE_SIZE; j < i * PAGE_SIZE + PAGE_SIZE; j++) {
                    page.add(this.srcLs.get(j));
                }
                this.lsOfPaged.add(new ArrayList<>(page));
                page.clear();
            }
        } else {
            totalPageNum = this.srcLs.size() / PAGE_SIZE + 1;
            ArrayList<T> page = new ArrayList<>(PAGE_SIZE);
            // array has remaining elements after divided by page_size
            for (int i = 0; i < totalPageNum - 1; i++) {
                for (int j = i * PAGE_SIZE; j < i * PAGE_SIZE + PAGE_SIZE; j++) {
                    page.add(this.srcLs.get(j));
                }
                this.lsOfPaged.add(new ArrayList<>(page));
                page.clear();
            }
            // last page
            for (int k = (totalPageNum - 1) * PAGE_SIZE; k < this.srcLs.size(); k++) {
                page.add(this.srcLs.get(k));
            }
            this.lsOfPaged.add(new ArrayList<>(page));
            page.clear();
        }
    }

    public List<T> getPaginated(int pageNum) {
        return this.lsOfPaged.get(pageNum - 1);
    }
}
