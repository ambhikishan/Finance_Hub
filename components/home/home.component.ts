import { Component, OnInit, OnDestroy } from '@angular/core';
 
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  images = [
    'assets/img1.png',
    'assets/Media.jpg',
    'assets/investment-8692176.jpg',
    'assets/img5.jpg',
    'assets/logo.png',
    'assets/img6.jpg',
    'assets/img 4.jpeg'
  ];
  currentIndex = 0;
  intervalId: any;
 
  ngOnInit() {
    this.startAutoSlide();
  }
 
  ngOnDestroy() {
    clearInterval(this.intervalId);
  }
 
  startAutoSlide() {
    this.intervalId = setInterval(() => {
      this.next();
    }, 3000); // Change image every 2 seconds
  }
 
  getStarted() {
    alert('Welcome to FinanceHub! Let\'s get started.');
  }
 
  prev() {
    this.currentIndex = (this.currentIndex > 0) ? this.currentIndex - 1 : this.images.length - 1;
  }
 
  next() {
    this.currentIndex = (this.currentIndex < this.images.length - 1) ? this.currentIndex + 1 : 0;
  }
 
}