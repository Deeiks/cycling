package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * BadMiniCyclingPortal is a minimally compiling, but non-functioning implementor
 * of the MiniCyclingPortalInterface interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0
 *
 */
public class BadMiniCyclingPortal implements MiniCyclingPortalInterface {
	static ArrayList<Race> races = new ArrayList<Race>();
	static ArrayList<Team> teams = new ArrayList<Team>();

	@Override
	public int[] getRaceIds() {
		int[] raceIDs =  new int[races.size()];
		int x = 0;
		for (Race r : races){
			raceIDs[x] =r.getId();
			x++;
		}
		return raceIDs;
	}

	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		try {
			InvalidNameException.checkName(name);
			for(Race r: races) {
				if (name == r.getName()) {
					throw new IllegalNameException("Name already used");
				}
			}
				races.add(new Race(name,description));
			}
		catch (IllegalNameException |InvalidNameException e) {

		}
			
		return 0;
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		try {
			for (Race r : races) {
				if(raceId == r.getId()) {
				 String raceDetails =  r.getName() +" " +r.getDesc();
				 //Add Stages and Total length
				 return raceDetails;
				}
			throw new IDNotRecognisedException("ID Not Recognised");
			}
		}
		catch(IDNotRecognisedException e) {
		}
		return null;
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		try{
			boolean check = false;
			for (Race r: races){
				if(raceId==r.getId()){
					races.remove(r);
					check = true;
					break;
				}
			}if(!check) throw new IDNotRecognisedException("ID Not Recognised");
		}
		catch(IDNotRecognisedException e){
			System.out.println(e);
		}
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		try{
			for (Race r: races){
				if(raceId==r.getId()) return r.getStages().size();
				}throw new IDNotRecognisedException("ID Not Recognised");
			}catch(IDNotRecognisedException e){
		}
		return 0;
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		try{
			InvalidNameException.checkName(stageName);
			IllegalNameException.checkStageName(races,stageName);
			if (length <5) throw new InvalidLengthException("Length of Stage to Short");
			for (Race r: races){
				if(raceId==r.getId()){
					r.addStage(stageName,description,length,startTime,type);
					return Stage.getCurId();
				}
			}throw new IDNotRecognisedException("ID Not Recognised");
		}catch (IDNotRecognisedException| IllegalNameException| InvalidNameException| InvalidLengthException e){

		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		try{
			for(Race r:races){
				if(raceId==r.getId()){
					ArrayList<Stage> stages = r.getStages();
					int []stageIds = new int[stages.size()];
					for(int i=0 ; i<stages.size(); i++) stageIds[i] = stages.get(i).getId();
					return stageIds;
				} throw new IDNotRecognisedException("ID Not Recognised");
			}
		}catch (IDNotRecognisedException e){
		}
		return null;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		try{
			for(Race r:races){
				for(Stage s:r.getStages()){
					if(s.getId()==stageId){
						return s.getStageLength();
					}
				}
			} throw new IDNotRecognisedException("ID Not Recognised");
		}catch (IDNotRecognisedException e){
		}
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		try{ boolean check = false;
			outerLoop : for(Race r:races){
				for(Stage s:r.getStages()){
					if(s.getId()==stageId){
						r.removeStage(s);
						check = true;
						break outerLoop;
					}
				}
			} if(!check) {throw new IDNotRecognisedException("ID Not Recognised");}
		}catch (IDNotRecognisedException e){
		}
	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		try{for (Race r:races){
			for (Stage s:r.getStages()) {
				if (stageId == s.getId()) {
					InvalidStageTypeException.checkStageType(s);
					InvalidStageStateException.checkStageState(s);
					Double currentRemainingLength = s.getStageLength();
					for (Segment seg : s.getSegments()) {
						currentRemainingLength -= seg.getSegmentLength();
					}
					if (currentRemainingLength < length | location > s.getStageLength()){
						throw new InvalidLocationException();
					}
					s.addSegment(location, length, type, averageGradient);
					return Segment.getCurId();
				}
			}
			}throw new IDNotRecognisedException("ID Not Recognised");
		} catch (IDNotRecognisedException|InvalidLocationException|InvalidStageStateException|InvalidStageTypeException
				e) {
		}

		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		try{for (Race r:races){
			for (Stage s:r.getStages()) {
				if (stageId == s.getId()) {
					InvalidStageTypeException.checkStageType(s);
					InvalidStageStateException.checkStageState(s);
					if (location > s.getStageLength()){
						throw new InvalidLocationException();
					}
					s.addSegment(location); // Finish THIS THING
					return Segment.getCurId();
				}
			}
		}throw new IDNotRecognisedException("ID Not Recognised");
		} catch (IDNotRecognisedException|InvalidLocationException|InvalidStageStateException|InvalidStageTypeException
				e) {
		}

		return 0;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		try{boolean check = false;
			outerLoop: for (Race r:races){
			for(Stage s:r.getStages()){
				for(Segment seg: s.getSegments()){
					if(seg.getId()==segmentId){
						InvalidStageStateException.checkStageState(s);
						s.removeSegment(seg);
						check = true;
						break outerLoop;
					}
				}
			}
		}
		if(check == false){throw new IDNotRecognisedException("ID Not Recognised");}
		}catch(InvalidStageStateException|IDNotRecognisedException e){
			System.out.println(e);
		}

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		try{boolean check = false;
			outerLoop: for (Race r: races){
				for(Stage s:r.getStages()){
					if(stageId == s.getId()){
						InvalidStageStateException.checkStageState(s);
						s.setStageState(StageState.WAITING_FOR_RESULTS);
						check = true;
						break outerLoop;
					}
				}
			}throw new IDNotRecognisedException("ID Not Recognised");
		}catch(IDNotRecognisedException|InvalidStageStateException e){
			System.out.println(e);
		}
	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		try{for(Race r: races){
			for(Stage s: r.getStages()){
				if(s.getId() == stageId){
					int[] segmentIds = new int[s.getSegments().size()];
					int i=0;
					for(Segment seg:s.getSegments()){
						segmentIds[i] = seg.getId();
					}
					return segmentIds;
				}
			}
			}throw new IDNotRecognisedException("ID Not Recognised");
		}catch (IDNotRecognisedException e){
			System.out.println(e);
		}
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		try {
			InvalidNameException.checkName(name);
			for(Team t: teams) {
				if (name == t.getName()) {
					throw new IllegalNameException("Name already used");
				}
			}
			teams.add(new Team(name,description));
		}
		catch (IllegalNameException |InvalidNameException e) {
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		try{
			boolean check = false;
			for (Team t: teams){
				if(teamId==t.getId()){
					teams.remove(t);
					check = true;
					break;
				}
			}if(!check) throw new IDNotRecognisedException("ID Not Recognised");
		}
		catch(IDNotRecognisedException e){
			System.out.println(e);
		}
	}

	@Override
	public int[] getTeams() {
		int[] teamIds = new int[teams.size()];
		int i = 0;
		for (Team t: teams){
			teamIds[i] = t.getId();
			i++;
		}
		return teamIds;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		try {
			for (Team t : teams) {
				if (t.getId() == teamId) {
					int[] riderIds = new int[t.getRiders().size()];
					if (t.getRiders().size() == 0) {
						return null;
					}
					int i = 0;
					for (Rider r : t.getRiders()) {
						riderIds[i] = r.getId();
						i++;
					}
					return riderIds;
				}
			}
			throw new IDNotRecognisedException("ID Not Recognised");
		} catch (IDNotRecognisedException e) {
			System.out.println(e);
		}
		return null;
	}
	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		try{
			if(name == null){throw new IllegalArgumentException("Name can't be empty");}
			if(yearOfBirth <1900){throw new IllegalArgumentException("Invalid Year of Birth");}
			for (Team t:teams){
				if(teamID == t.getId()){
					t.addRider(teamID,name,yearOfBirth);
				}
			} throw new IDNotRecognisedException("ID Not Recognised");
		}catch (IDNotRecognisedException|IllegalArgumentException e){
		}
		return 0;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		try{
			for(Team t:teams){
				for(Rider r: t.getRiders()){
					if(r.getId()==riderId){
						t.removeRider(r);
					}
				}
			}throw new IDNotRecognisedException("ID Not Recognised");
		}catch(IDNotRecognisedException e){
			System.out.println(e);
		}
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		try{
			for(Race r:races){
				for(Stage s: r.getStages()){
					if(s.getId()==stageId){
						if(s.getStageState()!=StageState.WAITING_FOR_RESULTS){
							throw new InvalidStageStateException("Invalid Stage State");
						}
						if(checkpoints.length != s.getStageLength() +2 ){
							throw new InvalidCheckpointsException("Invalid Amount of Checkpoints");
					}
						for(Team t:teams){
							for(Rider rid: t.riders){
								if(rid.getId() == riderId){
									
								}

								}
							}
						}
				}
			}

		}catch ()

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
