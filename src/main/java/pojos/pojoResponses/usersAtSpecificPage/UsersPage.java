package pojos.pojoResponses.usersAtSpecificPage;

import java.util.ArrayList;

public class UsersPage {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<UserData> data;
    public Support support;
    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<UserData> getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setData(ArrayList<UserData> data) {
        this.data = data;
    }

    public void setSupport(Support support) {
        this.support = support;
    }


}
