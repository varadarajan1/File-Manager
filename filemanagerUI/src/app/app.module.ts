import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgxPaginationModule} from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { FileService } from './upload-file.service';
import { FileViewComponent } from './file-view/file-view.component';
@NgModule({
  declarations: [
    AppComponent,
    UploadFileComponent,
    FileViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    HttpClientModule,
    ReactiveFormsModule,    
    FormsModule,
    NgxPaginationModule
  ],
  providers: [FileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
