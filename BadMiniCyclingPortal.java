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
			throw new IDNotRecognisedException("ID Not Recognized");
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

			}if(!check) throw new IDNotRecognisedException("ID Not Recognized");
		}
		catch(IDNotRecognisedException e){

		}

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		try{
			for (Race r: races){
				if(raceId==r.getId()) return r.getStages().size();
				}throw new IDNotRecognisedException("ID Not Recognized");
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
			}throw new IDNotRecognisedException("ID Not Recognized");
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
				} throw new IDNotRecognisedException("ID Not Recognized");
			}
		}catch (IDNotRecognisedException e){
		}
		return null;
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

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
