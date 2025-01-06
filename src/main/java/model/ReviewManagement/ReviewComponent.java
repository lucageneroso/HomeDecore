package model.ReviewManagement;

import model.ReviewManagement.Recensione;

import java.util.List;

public interface ReviewComponent{
double getRating();
List<Recensione> showReview();
}