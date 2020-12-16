package Challenge16;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketBundle {
    private HashMap<String,TicketRule> rules;
    private ArrayList<Long> yourTicket;
    private ArrayList<ArrayList<Long>> nearbyTickets;

    public TicketBundle(HashMap<String,TicketRule> rules, ArrayList<Long> yourTicket, ArrayList<ArrayList<Long>> nearbyTickets) {
        this.rules = rules;
        this.yourTicket = yourTicket;
        this.nearbyTickets = nearbyTickets;
    }

    public HashMap<String,TicketRule> getRules() {
        return rules;
    }

    public ArrayList<Long> getYourTicket() {
        return yourTicket;
    }

    public ArrayList<ArrayList<Long>> getNearbyTickets() {
        return nearbyTickets;
    }
}
