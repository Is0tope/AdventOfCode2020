package Challenge14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class BitMask {
    private char[] mask;

    public BitMask(char[] mask) {
        this.mask = mask;
    }

    public long apply(long num){
        String str = Long.toBinaryString(num);
        StringBuilder sb = new StringBuilder(str);
        for(int i= 0;i<36-str.length();i++){
            sb.insert(0,'0');
        }
        char[] numstr = sb.toString().toCharArray();
        String newstr = "";
        for(int i = 0; i < 36; i++){
            if(mask[i] == '1'){
                newstr+='1';
            }else if(mask[i] == '0'){
                newstr+='0';
            }else{
                newstr+=numstr[i];
            }
        }
        return Long.parseLong(newstr,2);
    }

    public ArrayList<String> runMask(char[] src, String current){
        ArrayList<String> ret = new ArrayList<>();
        if(current.length() == 36){
            ret.add(current);
        }else{
            int i = current.length();
            if(mask[i] == '1'){
                ret.addAll(runMask(src,current+'1'));
            }else if(mask[i] == '0'){
                ret.addAll(runMask(src,current+src[i]));
            }else{
                ret.addAll(runMask(src,current+'1'));
                ret.addAll(runMask(src,current+'0'));
            }
        }
        return ret;
    }

    public ArrayList<Long> applyMultiple(long num){
        String str = Long.toBinaryString(num);
        StringBuilder sb = new StringBuilder(str);
        for(int i= 0;i<36-str.length();i++){
            sb.insert(0,'0');
        }
        char[] src = sb.toString().toCharArray();
        ArrayList<String> options = runMask(src,"");
        return new ArrayList<Long>(options.stream().map((x) -> Long.parseLong(x,2)).collect(Collectors.toList()));
    }

}
