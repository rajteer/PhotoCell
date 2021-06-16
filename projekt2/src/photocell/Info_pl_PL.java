package photocell;

import java.util.ListResourceBundle;

public class Info_pl_PL extends ListResourceBundle 
{

	public Object[][] getContents() 
	{
	     return contents;
	}

	private Object[][] contents = 
	{
	     {  "english", "Angielski" },
	     {  "polish", "Polski" },
	     {  "saveButton", "Wyświetl dane" },
	     {  "darkTheme", "Tryb ciemny"},
	     {  "exitButton", "Zakończ"},
	     {  "startButton", "Rozpocznij"},
	     {  "stopButton", "Zatrzymaj"},
	     {  "waveLength", "Długość fali"},
	     {  "waveFrequency", "Częstotliwość fali"},
	     {  "waveIntensity", "Natężenie fali"},
	     {  "energy", "Energia fotonu"},
	     {  "showData", "Wyświetl dane"},
	     {  "aboutProgram", "O programie"},
	     {  "theoryInfo", "Wstęp teoretyczny"},
	     {	"Caesium", "Cs- Cez"},
	     {	"Calcium", "Ca- Wapń "},
	     {	"Rubidium", "Rb - Rubid"},
	     {	"Sodium", "Na - Sód"},
	     {	"Potassium", "K - Potas"},
	     {"aboutAuthors", "Aplikacja wykonana przez zespół Fotokomórka w składzie: Julia Sieruta oraz Paweł Rajter, w ramach projektu na przedmiot Programowanie obiektowe."},
	     {"ExitQuestion", "Czy napewno zakończyć?"},
	     {"Plot1", "Zależność napięcia hamowania od częstotliwości dla "},
	     {"Plot2", "Charakterystyka prądowo-napięciowa"},
	     {"SavePlot","Zapisz wykres jako PNG"} ,
	     {"SavePDF", "Zapisz wykresy jako PDF"},
	     {"FreVol", "częstotliwość,napięcie hamowania"},
	     {"error", "Coś się zepsuło"},
	     {"kom1", "Wartości powinny mieścić się w zakresie od 380nm do 780nm."},
	     {"numbers", "Proszę wprowadzić wartość, która jest liczbą."},
	     {"kom2", "Wartości powinny mieścić się w zakresie od 384THz do 789THz."},
	     {"kom3", "Wartości powinny mieścić się w zakresie od 0% do 100%."},
	     {"exit", "Zakończ"}
	};

}
