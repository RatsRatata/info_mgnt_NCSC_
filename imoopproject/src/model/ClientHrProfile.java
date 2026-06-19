//Getters and setters for the table client_hr_profile.

package model;

public class ClientHrProfile {
	private int skillId;
    private String referenceCode;
    private String technicalSkills;
    private String communityService;

    public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getTechnicalSkills() {
		return technicalSkills;
	}
	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	public String getCommunityService() {
		return communityService;
	}
	public void setCommunityService(String communityService) {
		this.communityService = communityService;
	}

}  