/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
