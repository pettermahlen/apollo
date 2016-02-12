#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

public class Track {
  private final String name;
  private final Album album;

  public String getName() {
    return name;
  }

  public Album getAlbum() {
    return album;
  }

  public Track(String name, Album album) {
    this.name = name;
    this.album = album;
  }
}
