package model.Review;

import model.Review.Recensione;

import java.util.List;

public interface ReviewComponent{
double getRating();
List<Recensione> showReview();
}