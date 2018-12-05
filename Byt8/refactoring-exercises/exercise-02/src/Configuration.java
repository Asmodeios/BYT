import java.util.*;

public class Configuration {
	public int interval;

	public int duration;

	public int departure;
	
	public int checkInterval(Properties props) throws ConfigurationException {
		String valueString = props.getProperty("interval");;
		int value;
		
		if (valueString == null) {
			throw new ConfigurationException("interval");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new ConfigurationException("interval <= 0");
		} 
		
		return value;
	}
	
	public int checkDuration(Properties props) throws ConfigurationException {
		String valueString = props.getProperty("duration");;
		int value;
		
		if (valueString == null) {
			throw new ConfigurationException("duration");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new ConfigurationException("duration <= 0");
		} else if ((value % interval) != 0) {
			throw new ConfigurationException("duration % interval");
		}
		
		return value;
	}

	public int checkDeparture(Properties props) throws ConfigurationException {
		String valueString = props.getProperty("departure");;
		int value;
		
		if (valueString == null) {
			throw new ConfigurationException("departure");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new ConfigurationException("departure <= 0");
		} else if ((value % interval) != 0) {
			throw new ConfigurationException("departure % interval");
		}
		
		return value;
	}
	/*
	 * Splitted one big method into smaller one
	 */
	public void load(Properties props) throws ConfigurationException {
		interval = checkInterval(props);

		duration = checkDuration(props);

		departure = checkDeparture(props);
	}
}
