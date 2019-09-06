package cn.cash360.advanced.binder.aidlMy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @time 2019/9/5 16:08
 * @desc
 */
public class Book implements Parcelable {

    private String bookName;
    private String userName;

    protected Book(Parcel in) {
        bookName = in.readString();
        userName = in.readString();
    }

    public Book(String bookName, String userName) {
        this.bookName = bookName;
        this.userName = userName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeString(userName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
