package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TransctionHistory implements Serializable{
	private String accno,trans_time,trans_type,trans_id;
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	private double amount,current_bal;
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCurrent_bal() {
		return current_bal;
	}
	public void setCurrent_bal(double current_bal) {
		this.current_bal = current_bal;
	}
	
	
	
}
