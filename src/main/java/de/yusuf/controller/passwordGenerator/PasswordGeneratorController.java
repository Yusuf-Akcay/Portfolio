package de.yusuf.controller.passwordGenerator;

import de.yusuf.controller.AbstractController;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;

@Controller( "passwordGeneratorController" )
@RequestScope
public class PasswordGeneratorController extends AbstractController {

  private final List<Character> specialCharacters = List.of( '.', ',', '#', '+', '-', '*', '/', '%', '$', '!', '?' );

  private int length;
  private boolean hasUppercase = true;
  private boolean hasLowercase = true;
  private boolean hasNumber = true;

  private Character[] hasSpecialCharacters;

  private String generatedPassword = "";

  @Override public void init() {}

  private List<Character> toCharList( String s ) {
    return s.chars().mapToObj( c -> (char) c ).toList();
  }

  private List<Character> fillIncludes() {
    final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    final List<Character> NUMBERS = Arrays.asList( '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' );
    final List<Character> CHARACTERS = Arrays.asList( hasSpecialCharacters );
    final List<Character> tmpList = new ArrayList<>();

    if ( hasUppercase )
      tmpList.addAll( toCharList( ALPHABET.toUpperCase( Locale.ROOT ) ) );

    if ( hasLowercase )
      tmpList.addAll( toCharList( ALPHABET ) );

    if ( hasNumber )
      tmpList.addAll( NUMBERS );

    if ( ! CHARACTERS.isEmpty() )
      tmpList.addAll( CHARACTERS );

    return tmpList;
  }

  public void generatePassword() {
    List<Character> passwordIncludes = fillIncludes();
    StringBuilder sb = new StringBuilder( length );
    if ( CollectionUtils.isNotEmpty( passwordIncludes ) )
      for ( int i = 0; i < length; i++ )
        sb.append( passwordIncludes.get( (new Random().nextInt( 0, passwordIncludes.size())) ) );
    generatedPassword = sb.toString();
  }

  public List<Character> getSpecialCharacters() {
    return specialCharacters;
  }

  public int getLength() {
    return length;
  }

  public void setLength( int length ) {
    this.length = length;
  }

  public boolean isHasUppercase() {
    return hasUppercase;
  }

  public void setHasUppercase( boolean hasUppercase ) {
    this.hasUppercase = hasUppercase;
  }

  public boolean isHasLowercase() {
    return hasLowercase;
  }

  public void setHasLowercase( boolean hasLowercase ) {
    this.hasLowercase = hasLowercase;
  }

  public boolean isHasNumber() {
    return hasNumber;
  }

  public void setHasNumber( boolean hasNumber ) {
    this.hasNumber = hasNumber;
  }

  public Character[] getHasSpecialCharacters() {
    return hasSpecialCharacters;
  }

  public void setHasSpecialCharacters( Character[] hasSpecialCharacters ) {
    this.hasSpecialCharacters = hasSpecialCharacters;
  }

  public String getGeneratedPassword() {
    return generatedPassword;
  }

  public void setGeneratedPassword( String generatedPassword ) {
    this.generatedPassword = generatedPassword;
  }
}
