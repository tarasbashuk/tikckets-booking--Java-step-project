package database;


import dao.FlightDAO;
import entities.Flight;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightsRandomaizer {

    private final String[] destinations = {"barcelona", "palma", "abu dhabi",
            "abuja",
            "accra",
            "addis ababa",
            "algiers",
            "amman",
            "amsterdam",
            "andorra la vella",
            "ankara",
            "antananarivo",
            "apia",
            "ashgabat",
            "asmara",
            "astana",
            "asuncion",
            "athens",
            "baghdad",
            "baku",
            "bamako",
            "bandar seri begawan",
            "bangkok",
            "bangui",
            "banjul",
            "basseterre",
            "beijing",
            "beirut",
            "belfast",
            "belgrade",
            "belmopan",
            "berlin",
            "bern",
            "bishkek",
            "bissau",
            "bogota",
            "brasilia",
            "bratislava",
            "brazzaville",
            "bridgetown",
            "brussels",
            "bucharest",
            "budapest",
            "buenos aires",
            "cairo",
            "canberra",
            "caracas",
            "cardiff",
            "castries",
            "cayenne",
            "chisinau",
            "colombo",
            "conakry",
            "copenhagen",
            "dakar",
            "damascus",
            "dhaka",
            "dili",
            "djibouti",
            "dodoma",
            "doha",
            "dublin",
            "dushanbe",
            "edinburgh",
            "freetown",
            "funafuti",
            "gaborone",
            "georgetown",
            "gitega",
            "guatemala city",
            "hanoi",
            "harare",
            "havana",
            "helsinki",
            "honiara",
            "islamabad",
            "jakarta",
            "juba",
            "kabul",
            "kampala",
            "kathmandu",
            "khartoum",
            "kigali",
            "kingston",
            "kingstown",
            "kinshasa",
            "kuala lumpur",
            "kuwait city",
            "la paz",
            "libreville",
            "lilongwe",
            "lima",
            "lisbon",
            "ljubljana",
            "lome",
            "london",
            "luanda",
            "lusaka",
            "luxembourg",
            "madrid",
            "majuro",
            "malabo",
            "male",
            "managua",
            "manama",
            "manila",
            "maputo",
            "maseru",
            "mbabana",
            "melekeok",
            "mexico city",
            "minsk",
            "mogadishu",
            "monaco",
            "monrovia",
            "montevideo",
            "moroni",
            "muscat",
            "ndjamena",
            "nairobi",
            "nassau",
            "nay pyi taw",
            "new delhi",
            "niamey",
            "nicosia",
            "nouakchott",
            "nukualofa",
            "oslo",
            "ottawa",
            "ouagadougou",
            "palikir",
            "panama city",
            "paramaribo",
            "paris",
            "phnom penh",
            "podgorica",
            "port au prince",
            "port louis",
            "port moresby",
            "port of spain",
            "port vila",
            "porto novo",
            "prague",
            "praia",
            "pretoria",
            "bloemfontein",
            "cape town",
            "pristina",
            "pyongyang",
            "quito",
            "rabat",
            "reykjavik",
            "riga",
            "riyadh",
            "rome",
            "roseau",
            "saint georges",
            "saint john",
            "san jose",
            "san marino",
            "san salvador",
            "sania",
            "santiago",
            "santo domingo",
            "sao tome",
            "sarajevo",
            "seoul",
            "singapore",
            "skopje",
            "sofia",
            "stockholm",
            "suva",
            "taipei",
            "tallinn",
            "tarawa atoll",
            "tashkent",
            "tbilisi",
            "tegucigalpa",
            "tehran",
            "tel aviv ",
            "thimphu",
            "tirana",
            "tokyo",
            "tripoli",
            "tunis",
            "ulaanbaatar",
            "vaduz",
            "valletta",
            "vatican city",
            "victoria",
            "vienna",
            "vientiane",
            "vilnius",
            "warsaw",
            "washington",
            "wellington",
            "windhoek",
            "yamoussoukro",
            "yaounde",
            "yerevan",
            "zagreb"
    };
    private final int arraySize = destinations.length;
    private final int quantity;


    public FlightsRandomaizer(int quantity) {
        this.quantity = quantity;
    }

    int generateValueFromRange(int min, int max) {
        Random r = new Random();
        int val = r.nextInt(max - min);
        return min + val;
    }

    public List<Flight> get() throws ParseException {
        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            flights.add(generateNewFlight());
        }
        return flights;
    }

    private String generateFlightNumber(String from, String destination, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(from.toUpperCase().charAt(0));
        sb.append(destination.toUpperCase().charAt(0));
        sb.append(id);
        return sb.toString();
    }

    private String generateDate() {
        int day = generateValueFromRange(1, 30);
        int month = generateValueFromRange(8, 12);
        String dayString = Integer.toString(day);
        if (dayString.length() == 1) dayString = "0"+dayString;
        String monthString = Integer.toString(month);
        if (monthString.length() == 1) monthString = "0"+monthString;

        int hour = generateValueFromRange(0, 24);
        StringBuilder sb = new StringBuilder();
        sb.append(dayString);
        sb.append(".");
        sb.append(monthString);
        sb.append(".");
        sb.append("2019");
        sb.append("-");
        sb.append(hour);
        sb.append(":");
        sb.append("00");
        return sb.toString();
    }

    private Flight generateNewFlight() throws ParseException {
        int id = generateValueFromRange(100, 1000);
        String destination = destinations[generateValueFromRange(0, arraySize)];
        int seatsMin = 100;
        int seatsMax = 350;
        int seats = generateValueFromRange(seatsMin, seatsMax);
        String from = "kyiv";
        String flightNumber = generateFlightNumber(from, destination, id);
        String date = generateDate();
        return new Flight(flightNumber, from, destination, date, seats);
    }


    public static void main(String[] args) throws ParseException {
        FlightsRandomaizer newFlights = new FlightsRandomaizer(1000);
        List<Flight> flights = newFlights.get();
        FlightDAO flDAO = new FlightDAO();
        for (int i = 0; i < flights.size(); i++) {
            flDAO.insert(flights.get(i));
        }
        flDAO.saveData();
    }
}
