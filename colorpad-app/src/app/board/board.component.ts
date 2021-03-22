import { Component, Input, OnInit } from '@angular/core';
import { ColorService } from '../color.service'

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.sass']
})
export class BoardComponent implements OnInit {

  @Input() selectedColor:string = ""

  row = 5
  col = 5
  board: number[][] = [];
  squares: string[] = Array(this.row * this.col).fill("yellow");
  
  constructor(private colorService:ColorService) {

  }

  ngOnInit(): void {

    this.reset()

    console.log(this.squares);

  }

  reset(){
    this.squares = Array(this.row * this.col).fill("white")
  }
  changeColor(i:number){
    console.log("changeColor")
    console.log(this.selectedColor)
    console.log(i)
    this.colorService.color(i, this.selectedColor).subscribe(
      (data) => {
        console.log(data.status)
        if (data.status == 200){
          this.squares[i] = this.selectedColor
        }
      },
      (error) => {
        this.squares[i] = "black"
      }
    )
  }

}
