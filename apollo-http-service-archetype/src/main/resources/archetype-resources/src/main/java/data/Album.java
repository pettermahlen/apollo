#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

public class Album {
  private final String name;
  private final Artist artist;

  public String getName() {
    return name;
  }

  public Artist getArtist() {
    return artist;
  }

  public Album(String name, Artist artist) {
    this.name = name;
    this.artist = artist;
  }
}
