package model;

public class ClientRelationship {
	private int relativeId;
    private String referenceCode;
    private String relativeName;
    private String relationship;
    private int relativeAge;
    private String workingStatus;
    private String occupation;
    private long income;
    
    public int getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public int getRelativeAge() {
		return relativeAge;
	}
	public void setRelativeAge(int relativeAge) {
		this.relativeAge = relativeAge;
	}
	public String getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public long getIncome() {
		return income;
	}
	public void setIncome(long income) {
		this.income = income;
	}

}