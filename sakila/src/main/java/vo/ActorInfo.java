package vo;

public class ActorInfo {
	// generate toString??

	@Override
		public String toString() {
			return "Actorinfo [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", filmInfo="
					+ filmInfo + "]";
		}
		
		
		public int getActorId() {
			return actorId;
		}
		public void setActorId(int actorId) {
			this.actorId = actorId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFilmInfo() {
			return filmInfo;
		}
		public void setFilmInfo(String filmInfo) {
			this.filmInfo = filmInfo;
		}
		private int actorId;
		private String firstName;
		private String lastName;
		private String filmInfo;
	}
