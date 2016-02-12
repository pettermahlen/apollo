#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

public class Artist {
  private final String name;

  public String getName() {
    return name;
  }

  public Artist(String name) {
    this.name = name;
  }
}
