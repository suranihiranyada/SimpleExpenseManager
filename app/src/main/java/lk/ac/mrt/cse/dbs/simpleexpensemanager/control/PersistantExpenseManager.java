package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistantTransactionDAO;
/**
 * Created by Surani Hiranyada on 11/19/2016.
 */

public class PersistantExpenseManager extends ExpenseManager  {

        private Context context;
        public PersistantExpenseManager(Context ctx){
            this.context = ctx;
            setup();
        }
        @Override
        public void setup(){
            //Open existing database 140678V or create new one

            SQLiteDatabase mydatabase = context.openOrCreateDatabase("140678V", context.MODE_PRIVATE, null);

            //If it's the first time, create the databases.
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                    "Account_no VARCHAR PRIMARY KEY," +
                    "Bank VARCHAR," +
                    "Holder VARCHAR," +
                    "Initial_amt REAL" +
                    " );");


            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                    "Transaction_id INTEGER PRIMARY KEY," +
                    "Account_no VARCHAR," +
                    "Type INT," +
                    "Amt REAL," +
                    "Log_date DATE," +
                    "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                    ");");



            //These two functions will hold our DAO instances in memory till the program exists
            PersistantAccountDAO accountDAO = new PersistantAccountDAO(mydatabase);

            setAccountsDAO(accountDAO);

            setTransactionsDAO(new PersistantTransactionDAO(mydatabase));
        }
}
