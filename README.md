Hello I am abhishek mishra and i haved upload advance java project.
but when you download this project and run it will give multiple exception becouse in your data base you don't have table.
So its my responsibity to give all required table which is used in that prject.

This is a very basics project you will easiealy understand when you see the project and you can you this project in your college projects and you
can also take references for this project this project i have develop in 2024. when i was learning full stack developement.

if you want springoot MVC or SpringBoot Rest or Microservices project you can DM me in my instagram handler i have aleready given my instagram profile in bottom of the file.

                                                      1) Book Store Management Project ->
                                                     ====================================== 

 1-> first table SQL> desc admin_credential;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 EMAIL                                     NOT NULL VARCHAR2(30)
 USERNAME                                  NOT NULL VARCHAR2(30)
 PASSWORD                                           VARCHAR2(30)
 NAME                                               VARCHAR2(30)

2-> second table SQL> desc bookdetails;  (this table is represent books in book store)
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 BCODE                                     NOT NULL VARCHAR2(20)
 BNAME                                              VARCHAR2(30)
 AUTHOR                                             VARCHAR2(30)
 PRICE                                              NUMBER(10,2)
 QTY                                                NUMBER(10)


3-> third table  SQL> desc USER_BOOKS;  (this table responsible for user purchased booked)
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 USERID                                             VARCHAR2(20)
 BCODE                                              VARCHAR2(20)
 QTY                                                NUMBER(5)

4-> fourth table SQL> desc user_credential;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 USERID                                    NOT NULL VARCHAR2(20)
 UNAME                                              VARCHAR2(20)
 EMAIL                                              VARCHAR2(30)
 PHNO                                               NUMBER(11)
 CITY                                               VARCHAR2(20)
 PASS                                               VARCHAR2(20)



                                                     2) Banking Application Project
                                          ================================================== 

1-> first table SQL> desc BANK_CUSTOMERS;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ACCNO                                     NOT NULL VARCHAR2(20)
 FIRST_NAME                                         VARCHAR2(20)
 LAST_NAME                                          VARCHAR2(20)
 DOB                                                DATE
 USERNAME                                           VARCHAR2(20)
 EMAIL                                              VARCHAR2(20)
 PHONE_NO                                           NUMBER(11)
 CITY                                               VARCHAR2(20)
 STATE                                              VARCHAR2(20)
 PIN_CODE                                           NUMBER(6)
 BALANCE                                            NUMBER(10,2)
 OPENING_DATE                                       DATE
 NATIONALITY                                        VARCHAR2(20)
 ACC_TYPE                                           VARCHAR2(20)
 GENDER                                             VARCHAR2(6)
 BRANCH_CODE                                        VARCHAR2(20)


2-> second table SQL> desc USER_LOGIN;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 FULLNAME                                           VARCHAR2(30)
 EMAIL                                     NOT NULL VARCHAR2(30)
 USERNAME                                  NOT NULL VARCHAR2(20)
 PASSWORD                                           VARCHAR2(20)

3-> thired table  SQL> desc transaction_log;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ACCNO                                              VARCHAR2(20)
 AMOUNT                                             NUMBER(10,2)
 TRANS_TIME                                         TIMESTAMP(6)
 TRANS_TYPE                                         VARCHAR2(20)
 CURRENT_BAL                                        NUMBER(10,2)
 TRANSACTION_ID                                     VARCHAR2(20)

======================================================================= Contact Info =============================================================================

i hope you will understand when you see the table. if you have any query related to project u can contact me.
instagram -> abhishek_mishrark
facebook - Rishi Mishra
youtube -> Abhishek Mishra Vits

=========================================================  Thank You =============================================================================


