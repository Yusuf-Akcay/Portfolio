package de.yusuf.controller.formular;

import de.yusuf.controller.AbstractController;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller( "formularController" )
@SessionScope
public class FormularController extends AbstractController  {

  private Gender gender;
  private Title title;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;

  private String salaryExpectation;
  private Date dateDe = Date.from( LocalDate.now().plusDays( 1 ).atStartOfDay( ZoneId.systemDefault()).toInstant() );
  private Date minDate = Date.from( LocalDate.now().atStartOfDay( ZoneId.systemDefault() ).toInstant() );
  private String foundAt;
  private String place;
  private String notification;

  private UploadedFile uploadedFileLetter, uploadedFileResume;
  private List<UploadedFile> uploadedFileOtherFiles;
  private List<UploadedFile> uploadedFileTestimonials;

  private boolean checked;

  private boolean finDlg = false;
  private int finCounter = 10;

  public Gender[] getGenders() { return Gender.values(); }
  public Title[] getTitles() { return Title.values(); }

  @Override public void init() {
  }

  private boolean checkForLetter( String s) {
    if (s == null) return false;

    for (int i = 0; i < s.length(); i++)
      if (Character.isLetter( s.charAt( i ) )) {
        message( FacesMessage.SEVERITY_ERROR, "Wert enthält Buchstaben.", "Bitte nur Zahlen eingeben." );
        return false;
      }

    return true;
}

  public void check(){
      checked = gender != null &&
                firstname != null &&
                lastname != null &&
                email != null &&
                checkForLetter( phone ) &&
                checkForLetter( salaryExpectation ) &&
                foundAt != null &&
                place != null &&
                uploadedFileLetter != null &&
                uploadedFileResume != null;
  }

  private UploadedFile upload( FileUploadEvent fileUploadEvent ) {
    message( FacesMessage.SEVERITY_INFO , fileUploadEvent.getFile().getFileName(), "wurde eingefügt." );
    return fileUploadEvent.getFile();
  }

  private List<UploadedFile> upload( FilesUploadEvent filesUploadEvent ) {
    List<UploadedFile> list = new ArrayList();
    filesUploadEvent.getFiles().getFiles().forEach( uploadedFile -> {
      message( FacesMessage.SEVERITY_INFO , uploadedFile.getFileName(), "wurde eingefügt." );
      list.add( uploadedFile );
    } );
    return list;
  }

  public void uploadFileLetter( FileUploadEvent event ){
    setUploadedFileLetter( upload( event ) );
  }

  public void uploadFileResume( FileUploadEvent event ){
    setUploadedFileResume( upload( event ) );
  }

  public void uploadFileTestimonials( FilesUploadEvent event ){
    uploadedFileTestimonials = new ArrayList<>( upload( event ));
  }

  public void uploadFileOtherFiles( FilesUploadEvent event ){
    uploadedFileOtherFiles = new ArrayList<>( upload( event ));
  }

  private UploadedFile delete( UploadedFile uploadedFile ){
    log.info( "{}" , uploadedFile.getFileName() + " wurde gelöscht" );
    message( FacesMessage.SEVERITY_WARN , uploadedFile.getFileName(), "wurde entfernt." );
    return null;
  }

  private void delete( UploadedFile uploadedFile, List<UploadedFile> uploadedFileList ){
    log.info( "{}" , uploadedFile.getFileName() + " wurde gelöscht" );
    message( FacesMessage.SEVERITY_WARN , uploadedFile.getFileName(), "wurde entfernt." );
    uploadedFileList.remove( uploadedFile );
  }

  public void removeUploadedLetter(){
    setUploadedFileLetter( delete( uploadedFileLetter ) );
  }

  public void removeUploadedResume(){
    setUploadedFileResume( delete( uploadedFileResume ) );
  }

  public void removeUploadedTestimonials( UploadedFile uploadedFile ){
    delete( uploadedFile, uploadedFileTestimonials );
  }

  public void removeUploadedOtherFiles( UploadedFile uploadedFile ){
    delete( uploadedFile, uploadedFileOtherFiles );
  }

  public void confirm(){
    finDlg = true;
  }

  public void decrimFinCount(){
    finCounter--;
  }

  public void removeAll(){
    gender = null;
    title = null;
    firstname = null;
    lastname = null;
    email = null;
    phone = null;
    salaryExpectation = null;
    dateDe = Date.from( LocalDate.now().plusDays( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant());
    foundAt = null;
    place = null;
    notification = null;
    uploadedFileLetter = null;
    uploadedFileResume = null;
    uploadedFileTestimonials = null;
    uploadedFileOtherFiles = null;
    checked = false;
    finDlg = false;
    finCounter = 10;
  }

  public enum Gender {
    MALE( "Herr" ), FEMALE( "Frau" ), DIVERSE( "Diverse" );
    private final String label;
    Gender( String label ) {
      this.label = label;
    }
    public String getLabel() { return label; }
  }

  public enum Title {
    DR( "Dr." ), PROF( "Prof." );
    private final String label;
    Title( String label ) {
      this.label = label;
    }
    public String getLabel() { return label; }
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender( Gender gender ) {
    this.gender = gender;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle( Title title ) {
    this.title = title;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname( String firstname ) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname( String lastname ) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone( String phone ) {
    this.phone = phone;
  }

  public String getSalaryExpectation() {
    return salaryExpectation;
  }

  public void setSalaryExpectation( String salaryExpectation ) {
    this.salaryExpectation = salaryExpectation;
  }

  public Date getDateDe() {
    return dateDe;
  }

  public void setDateDe( Date dateDe ) {
    this.dateDe = dateDe;
  }

  public Date getMinDate() {
    return minDate;
  }

  public void setMinDate( Date minDate ) {
    this.minDate = minDate;
  }

  public String getFoundAt() {
    return foundAt;
  }

  public void setFoundAt( String foundAt ) {
    this.foundAt = foundAt;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace( String place ) {
    this.place = place;
  }

  public String getNotification() {
    return notification;
  }

  public void setNotification( String notification ) {
    this.notification = notification;
  }

  public UploadedFile getUploadedFileLetter() {
    return uploadedFileLetter;
  }

  public void setUploadedFileLetter( UploadedFile uploadedFileLetter ) {
    this.uploadedFileLetter = uploadedFileLetter;
  }

  public UploadedFile getUploadedFileResume() {
    return uploadedFileResume;
  }

  public void setUploadedFileResume( UploadedFile uploadedFileResume ) {
    this.uploadedFileResume = uploadedFileResume;
  }

  public List<UploadedFile> getUploadedFileOtherFiles() {
    return uploadedFileOtherFiles;
  }

  public void setUploadedFileOtherFiles(
      List<UploadedFile> uploadedFileOtherFiles ) {
    this.uploadedFileOtherFiles = uploadedFileOtherFiles;
  }

  public List<UploadedFile> getUploadedFileTestimonials() {
    return uploadedFileTestimonials;
  }

  public void setUploadedFileTestimonials(
      List<UploadedFile> uploadedFileTestimonials ) {
    this.uploadedFileTestimonials = uploadedFileTestimonials;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked( boolean checked ) {
    this.checked = checked;
  }

  public boolean isFinDlg() {
    return finDlg;
  }

  public void setFinDlg( boolean finDlg ) {
    this.finDlg = finDlg;
  }

  public int getFinCounter() {
    return finCounter;
  }

  public void setFinCounter( int finCounter ) {
    this.finCounter = finCounter;
  }
}