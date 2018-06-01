package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignment {
	
	   private String name;
	    private String deadline;
	    private String description;
	    private Laboratory laboratory;
	//    private Long idlaboratory;
		public Assignment(String name, String deadline, String description, Laboratory laboratory) {
			super();
			this.name = name;
			this.deadline = deadline;
			this.description = description;
			this.laboratory = laboratory;
		}
		public Assignment() {
			
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDeadline() {
			return deadline;
		}
		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	/*	public Long getIdlaboratory() {
			return idlaboratory;
		}
		public void setIdlaboratory(Long idlaboratory) {
			this.idlaboratory = idlaboratory;
		}*/
		
		
		@Override
		public String toString() {
			return "Assignment [name=" + name + ", deadline=" + deadline + ", description=" + description
					+ ", laboratory=" + laboratory + "]";
		}
		public Laboratory getLaboratory() {
			return laboratory;
		}
		public void setLaboratory(Laboratory laboratory) {
			this.laboratory = laboratory;
		}

	    

}
