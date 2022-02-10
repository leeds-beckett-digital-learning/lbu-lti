/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.state;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;
import uk.ac.leedsbeckett.lti.LtiConfiguration;

/**
 *
 * @author jon
 */
public class LtiState
{
  static final Random random = new Random( System.currentTimeMillis() );

  String id;
  String nonce;
  long timestamp;
  LtiConfiguration.Client client;

  public LtiState( LtiConfiguration.Client client )
  {
    id = UUID.randomUUID().toString();
    byte[] noncebytes = new byte[32];
    random.nextBytes( noncebytes );
    BigInteger bignonce = new BigInteger( 1, noncebytes );
    nonce = bignonce.toString( 16 );
    while ( nonce.length() < 64 )
      nonce = "0" + nonce;
    timestamp = System.currentTimeMillis();
    this.client = client;
  }

  public String getId()
  {
    return id;
  }

  public String getNonce()
  {
    return nonce;
  }

  public void clearNonce()
  {
    nonce = null;
  }

  public long getTimestamp()
  {
    return timestamp;
  }

  public LtiConfiguration.Client getClient()
  {
    return client;
  }


}
