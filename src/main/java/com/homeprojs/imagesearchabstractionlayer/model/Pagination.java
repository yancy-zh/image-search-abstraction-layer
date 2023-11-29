package com.homeprojs.imagesearchabstractionlayer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Pagination<T> {
    private final List<T> srcLs;
    private final List<List<T>> lsOfPaged = new ArrayList<>();
    private final int PAGE_SIZE;

    public Pagination(List<T> ls, int pageSize) {
        super();
        this.srcLs = ls;
        this.PAGE_SIZE = pageSize;
        divideIntoPages();
    }

    private void divideIntoPages() {
        int totalPageNumsum;
        if (this.srcLs.size() % PAGE_SIZE == 0) {
            totalPageNumsum = this.srcLs.size() / PAGE_SIZE;
            // array has exactly a divisible num of elements
            for (int i = 0; i < totalPageNumsum; i++) {
                List<T> selectedList = new ArrayList<>();
                for (int j = i * PAGE_SIZE; j < i * PAGE_SIZE + PAGE_SIZE; j++) {
                    selectedList.add(this.srcLs.get(j));
                }
                this.lsOfPaged.add(selectedList);
            }
        } else {
            totalPageNumsum = this.srcLs.size() / PAGE_SIZE + 1;
            // array has remaining elements after divided by page_size
            List<T> selectedList = new ArrayList<>();
            for (int i = 0; i < totalPageNumsum - 1; i++) {
                for (int j = i * PAGE_SIZE; j < i * PAGE_SIZE + PAGE_SIZE; j++) {
                    selectedList.add(this.srcLs.get(j));
                }
                this.lsOfPaged.add(selectedList);
                selectedList.clear();
            }
            // last page
            for (int k = (totalPageNumsum - 1) * PAGE_SIZE; k < this.srcLs.size(); k++) {
                selectedList.add(this.srcLs.get(k));
            }
            this.lsOfPaged.add(selectedList);
            selectedList.clear();
        }
    }

    public List<T> getPaginated(int pageNum) {
        return this.lsOfPaged.get(pageNum - 1);
    }
}
