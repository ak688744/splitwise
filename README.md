# Getting Started
## Requirements
    Please install Java 17 jdk to run the code. 

## Setup
### Option1:
    Clone the repo from github.
    Open project in Intellij.
    Right click on build.gradle.kts and click on Link Gradle Project.
    Wait for all dependecies to get downloaded
    Right click on DemoApplication.java File and click on Run
### Option2:
    Download demo-0.0.1-SNAPSHOT.jar from the mail
    Run below command to start the application
        java -jar {Download_path}/demo-0.0.1-SNAPSHOT.jar
        
    Example:
        java -jar /Users/vaibhav/Downloads/demo/build/libs/demo-0.0.1-SNAPSHOT.jar


#### LLD Discussion
    Splitwise:

Requirements:

createUser(String name): 
createGroup(String name, String userName)
addPeopleInGroup(String groupName, List<String> userName)
removePeopleInGroup(String groupName, List<String> userName) -- OPtional

AddExpenses -- ExpenseType (Percentage, EQUAL, UNEQUAL)
addExpense(createExpenseRequest)
modifyExpense(createExpenseRequest)
deleteExpense(String title)

showBalances - (String name, UserLevel/Group Level)
ShowLedger(String name, UserLevel/GroupLevel))
settleUpBalances(String borrower, String lender, DOuble amount, String user/Group, String groupName)
simplifyDebt -- Optional


ApiRequestModel:
	createExpenseRequest {
		String title;
		List<ExpenseDetail> borrower;
		List<ExpenseDetail> paidUsed
		ExpenseType expenseType;
		Double totalAmount;
		ExpenseDetail{
			String userName;
			Integer value;
		}
	}

Entities: 

User {
	String name;
}

Group {
	String name; 
}

UserGroupMapping{
	String userName;
	String groupName;
}

Expense {
	String createdBy;
	Double amount;
	ExpenseType expenseType;
	String groupName;
	String title;
	List<Transaction> transaction;
}

Transaction {
	String lenderUser;
	String borrowerUser;
	Double amount;
	
}


Credit {
	String createdBy
	Double amount;
	String groupName;
	List<Dues> dues
}





