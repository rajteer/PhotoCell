package photocell;

import java.util.ListResourceBundle;

public class Info_en_GB extends ListResourceBundle 
{

	public Object[][] getContents() 
	{
	     return contents;
	}

	private Object[][] contents = 
	{
	     {  "english", "Englisz" },
	     {  "polish", "Polish" },
	     {  "saveButton", "Show data" },
	     {  "darkTheme", "Dark theme"},
	     {  "exitButton", "Exit"},
	     {  "startButton", "Resume"},
	     {  "stopButton", "Stop"},
	     {  "waveLength", "Wave length"},
	     {  "waveFrequency", "Wave frequeency"},
	     {  "waveIntensity", "Wave intensity"},
	     {  "energy", "Photon energy"},
	     {  "showData", "Show data"},
	     {  "aboutProgram", "About program"},
	     {  "theoryInfo", "Theoretic information"},
	     {	"Caesium", "Cs- Caesium"},
	     {	"Calcium", "Ca- Calcium "},
	     {	"Rubidium", "Rb - Rubidium"},
	     {	"Sodium", "Na - Sodium"},
	     {	"Potassium", "K - Potassium"},
	     {	"aboutAuthors", "Application made by Fotokomorka team: Julia Sieruta and Pawel Rajter for the subject Object Oriented Programming."},
	     {	"ExitQuestion", "Do you want to close?"},
	     {"Plot1", "Stopping voltage as a function of frequency on "},
	     {"Plot2", "Current-voltage characteristic"},
	     {"SavePlot","Save plot as PNG"} ,
	     {"SavePDF", "Save plots as PDF"},
	     {"FreVol", "frequency, stopping voltage"},
	     {"error", "Error"},
	     {"kom1", "The values should be between 380nm and 780nm."},
	     {"numbers", "Please enter a value that is a number."},
	     {"kom2", "The values should be in the range from 384THz to 789THz."},
	     {"kom3", "The values should be in the range from 0% to 100%."},
	     {"exit", "Exit"}
	};


}
