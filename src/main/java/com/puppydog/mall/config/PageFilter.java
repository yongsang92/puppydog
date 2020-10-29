package com.puppydog.mall.config;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageFilter {

    private static final int DEFAULT_SIZE=12;
    private static final int DEFAULT_MAX_SIZE=30;

    private int page;
    private int size;

    public PageFilter(){
        this.page=0;
        this.size=DEFAULT_SIZE;
    }
    public void setPage(int page){
        this.page=page;
    }

    public void setSize(int size){
        this.size= size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE :size;
    }
    
    public Pageable makePageable(){
        return PageRequest.of(this.page,size);
    }

}