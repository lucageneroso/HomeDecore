package model.Review;

import model.Review.Recensione;

import java.util.List;

public class ReviewGroup implements ReviewComponent{
  List<Recensione> group;

    public ReviewGroup(){}
    public ReviewGroup(List<Recensione> group){
        this.group = group;
    }

    @Override
    public double getRating(){
        int somma=0;
        for (Recensione r: group){
            somma+=r.getRating();
        }
        return somma/(group.size());
    }

    @Override
    public List<Recensione> showReview(){
        for(Recensione r: group){
            System.out.println(r);
        }
        return group;
    }
}