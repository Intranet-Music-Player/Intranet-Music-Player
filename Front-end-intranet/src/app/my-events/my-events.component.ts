import {Component} from "@angular/core";
import {FormGroup, FormControl} from "@angular/forms";
import {IStarRatingOnClickEvent, IStarRatingOnRatingChangeEven} from "angular-star-rating/src/star-rating-struct";
 
@Component({
  selector: 'app-my-events',
  templateUrl: './my-events.component.html',
  styleUrls: ['./my-events.component.css']
})
export class MyEventsComponent {

  constructor() { }

 onClickResult:IStarRatingOnClickEvent;
    onRatingChangeResult:IStarRatingOnRatingChangeEven;
 
    onClick = ($event:IStarRatingOnClickEvent) => {
        console.log('onClick $event: ', $event);
        this.onClickResult = $event;
    };
 
    onRatingChange = ($event:IStarRatingOnRatingChangeEven) => {
        console.log('onRatingUpdated $event: ', $event);
        this.onRatingChangeResult = $event;
    };

}
