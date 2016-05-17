package com.it.tujia.module.search.acitivity;

import java.util.Date;

/**
 * Created by kkguo on 2015/11/25.
 */
public class DateSelect {
  private   Date first;
  private    Date last;
   private  int searchType;

    public Date getFirst() {
        return first;
    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
