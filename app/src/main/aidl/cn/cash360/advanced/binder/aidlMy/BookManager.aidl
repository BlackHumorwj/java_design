// BookManager.aidl
package cn.cash360.advanced.binder.aidlMy;

// Declare any non-default types here with import statements
import cn.cash360.advanced.binder.aidlMy.Book;

interface BookManager {

            void addBook(in Book book);
            List<Book> getList();
}
