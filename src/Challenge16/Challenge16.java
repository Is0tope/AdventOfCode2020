package Challenge16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Challenge16 {

    public static void main(String[] args) {
        Long resA = partA(process("src/Challenge16/input.txt"));
        System.out.println("Part A: " + resA.toString());
        Long resB = partB(process("src/Challenge16/input.txt"),"departure");
        System.out.println("Part B: " + resB.toString());
    }

    public static TicketBundle process(String fname) {
        File file = new File(fname);
        try {
            Scanner reader = new Scanner(file);
            // Rules first
            HashMap<String,TicketRule> rules = new HashMap<>();
            while(true){
                String l = reader.nextLine();
                if(l.equals("")){
                    break;
                }
                Pattern pat = Pattern.compile("([ \\w]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
                Matcher m = pat.matcher(l);
                if(m.find()){
                    TicketRule r = new TicketRule(m.group(1),Long.parseLong(m.group(2)),Long.parseLong(m.group(3)),Long.parseLong(m.group(4)),Long.parseLong(m.group(5)));
                    rules.put(m.group(1),r);
                }
            }
            // Your Ticket
            reader.nextLine();
            ArrayList<Long> yourTicket = new ArrayList<Long>(Arrays.stream(reader.nextLine().split(",")).map(x -> Long.parseLong(x)).collect(Collectors.toList()));
            // Other Tickets
            reader.nextLine();
            reader.nextLine();
            ArrayList<ArrayList<Long>> nearbyTickets = new ArrayList<>();
            while(reader.hasNextLine()){
                String l = reader.nextLine();
                ArrayList<Long> ticket = new ArrayList<Long>(Arrays.stream(l.split(",")).map(x -> Long.parseLong(x)).collect(Collectors.toList()));
                nearbyTickets.add(ticket);
            }

            return new TicketBundle(rules,yourTicket,nearbyTickets);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static long partA(TicketBundle bundle) {
        HashMap<String,TicketRule> rules = bundle.getRules();
        ArrayList<ArrayList<Long>> nearbyTickets = bundle.getNearbyTickets();

        long errorRate = 0;
        for(ArrayList<Long> ticket : nearbyTickets){
            for(long n : ticket){
                boolean valid = false;
                for(TicketRule r : rules.values()){
                    if(r.validate(n)){
                        valid = true;
                        break;
                    }
                }
                if(!valid){
                    errorRate += n;
                }
            }
        }
        return errorRate;
    }

    public static long partB(TicketBundle bundle, String target) {
        HashMap<String,TicketRule> rules = bundle.getRules();
        ArrayList<Long> yourTicket = bundle.getYourTicket();
        ArrayList<ArrayList<Long>> nearbyTickets = bundle.getNearbyTickets();

        ArrayList<ArrayList<Long>> validTickets = new ArrayList<>();
        for(ArrayList<Long> ticket : nearbyTickets){
            boolean ticketValid = true;
            for(long n : ticket){
                boolean valid = false;
                for(TicketRule r : rules.values()){
                    if(r.validate(n)){
                        valid = true;
                        break;
                    }
                }
                if(!valid){
                    ticketValid = false;
                }
            }
            if(ticketValid){
                validTickets.add(ticket);
            }
        }

        int length = validTickets.get(0).size();
        HashMap<String,Integer> fieldMap = new HashMap<>();
        while(fieldMap.size() < rules.size()){
            for(int i = 0;i < length;i++){
                Set<String> possible = new HashSet(rules.keySet());
                possible.removeAll(fieldMap.keySet());
                for(ArrayList<Long> ticket : validTickets){
                    for(String field : possible){
                        TicketRule rule = rules.get(field);
                        long num = ticket.get(i);
                        boolean valid = rule.validate(num);
                        if(!valid){
                            possible.remove(field);
                            break;
                        }
                    }
                }
                if(possible.size() == 1){
                    fieldMap.put(possible.iterator().next(),i);
                }
            }
        }
        long total = 1;
        for(String k : fieldMap.keySet()){
            if(k.startsWith(target)){
                total *= yourTicket.get(fieldMap.get(k));
            }
        }
        return total;
    }
}
