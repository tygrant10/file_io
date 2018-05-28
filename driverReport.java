import java.util.*;
import java.io.*;
public class driverReport {

    // Store driver total miles
    private HashMap<String, Double> driverMiles = new HashMap<>();
    // Store driver total trip time
    private HashMap<String, Integer> driverMinutes = new HashMap<>();
    // store driver MPH
    private HashMap<String, Integer> driverMPH = new HashMap<>();

    public HashMap<String, Integer> getDriverMinutes() {
        return this.driverMinutes;
    }

    public HashMap<String, Double> getDriverMiles() {
        return this.driverMiles;
    }

    public HashMap<String, Integer> getDriverMPH() {
        return this.driverMPH;
    }



    public void readDriverFile(String fileName) {
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                // splits each line into a string array
                String[] lineData = line.split(" ");
                if (lineData[0].equals("Driver") && !driverMinutes.containsKey(lineData[1])) {
                    this.driverMinutes.put(lineData[1], 0);
                    this.driverMiles.put(lineData[1], 0.0);
                } else if (lineData[0].equals("Trip")) {
                    int tripMinutes = getMinutes(lineData[2], lineData[3]);
                    double miles = Double.parseDouble(lineData[4]);
                    if (this.driverMinutes.containsKey(lineData[1]) && this.driverMiles.containsKey(lineData[1])) {
                        this.driverMinutes.replace(lineData[1], driverMinutes.get(lineData[1]) + tripMinutes);
                        this.driverMiles.replace(lineData[1], this.driverMiles.get(lineData[1]) + miles);
                    }
                } else {
                    System.out.println("Line not used");
                }
            }
            // always close file streams
            bufferedReader.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            fnf.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error with input file");
            e.printStackTrace();
        }
    }

    public int getMinutes(String startTime, String endTime) {
        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMinutes = ((startHour * 60) + Integer.parseInt(startTime.substring(3, 5)));
        int endHour = Integer.parseInt(endTime.substring(0, 2));
        int endMinutes = ((endHour * 60) + Integer.parseInt(endTime.substring(3, 5)));
        return endMinutes - startMinutes;
    }

    public void getDriverData() {
        String fileName = "driverDataOutput.txt";
        try {
            PrintWriter outputStream = new PrintWriter(fileName);
            for (String name : this.driverMiles.keySet()) {
                // calcualte mph
                int mph = (int) Math.ceil((this.driverMiles.get(name) * 60) / this.driverMinutes.get(name));
                this.driverMPH.put(name, mph);
                if((mph < 5 )|| (mph > 100)) {
                    driverMPH.remove(name);
                    driverMiles.remove(name);
                    driverMinutes.remove(name);
                }

            }
            // sort driver miles largest to smallest
            for (String name : this.driverMiles.keySet()) {
                outputStream.println(name + ": " + Math.round(this.driverMiles.get(name)) + " miles @ " + this.driverMPH.get(name) + " mph");
            }
            outputStream.close();
            System.out.println("Output File Created");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        driverReport test = new driverReport();
        try (Scanner userInput = new Scanner(System.in)) {

            // Get the location of the source file
            File sourceFile;
            System.out.println("What is the input file?");
            String path = userInput.nextLine();
            // Validate the input file
            sourceFile = new File(path);
            if (sourceFile.exists() == false) {
                System.out.println(path + " does not exist");
                System.exit(1);
            } else if (sourceFile.isFile() == false) {
                System.out.println(path + " is not a file");
                System.exit(1);
            }
            test.readDriverFile(path);
            test.getDriverData();
        }
    }
}