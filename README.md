# haveno-pricenode

## Overview

The Haveno pricenode is a simple HTTP service that fetches, transforms and relays data from third-party price providers to Haveno exchange clients on request.

Monero exchange rates are available at `/getAllMarketPrices`.

Pricenodes are deployed in production as Tor hidden services. This is not because the location of these nodes needs to be kept secret, but rather so that Haveno exchange clients do not need to exit the Tor network in order to get price data.

Anyone can run a pricenode, but it must be _discoverable_ in order for it to do any good. For exchange clients to discover your pricenode, its .onion address must be hard-coded in the Haveno exchange client's `ProvidersRepository` class. Alternatively, users can point explicitly to given pricenode (or set of pricenodes) with the exchange client's `--providers` command line option.

Pricenodes can be deployed anywhere Java and Tor binaries can be run. Instructions below cover deployment on localhost, and instructions [how to deploy on Heroku](README-HEROKU.md) are also available.

Pricenodes should be cheap to run with regard to both time and money. The application itself is non-resource intensive and can be run on the low-end of most providers' paid tiers.

A pricenode operator's main responsibilities are to ensure their node(s) are available and up-to-date. Releases are currently source-only, with the assumption that most operators will favor Git-based "push to deploy" workflows. To stay up to date with releases, operators can [subscribe to this repository's releases.atom feed](https://github.com/haveno-dex/pricenode/releases.atom).

Operating a production pricenode is a valuable service to the Haveno network.


## Prerequisites for running a pricenode

To run a pricenode, you will need:

  - JDK 8 if you want to build and run a node locally.
  - The `tor` binary (e.g. `brew install tor`) if you want to run a hidden service locally.
  
## Building source code

This repo has a dependency on git submodule [haveno](https://github.com/haveno-dex/haveno).  There are two ways to clone it before it can be compiled:

```
# 1) Use the --recursive option in the clone command:
$ git clone --recursive https://github.com/haveno-dex/haveno-pricenode.git

# 2) Do a normal clone, and pull down the haveno repo dependency with two git submodule commands:
$ git clone https://github.com/haveno-dex/haveno-pricenode.git
$ cd haveno-pricenode
$ git submodule init
$ git submodule update
```

To build:
```
$ ./gradlew clean build
```


## How to deploy for production

### Install

Run the one-command installer:

```bash
curl -s https://raw.githubusercontent.com/haveno-dex/haveno-pricenode/main/scripts/install_pricenode_debian.sh | sudo bash
```

At the end of the installer script, it should print your Tor onion hostname.

### Test

To manually test the endpoint, run the following:

``` bash
curl http://localhost:8078/getAllMarketPrices
```

### Monitoring

If you run a main pricenode, you also are obliged to activate the monitoring feed by running

```bash
bash <(curl -s https://raw.githubusercontent.com/haveno-dex/haveno-monitor/main/scripts/install_collectd_debian.sh)
```
Follow the instruction given by the script and report your certificate to the [@haveno-dex/monitoring](https://github.com/orgs/haveno-dex/teams/monitoring-operators) team.

Furthermore, you are obliged to provide network size data to the monitor by running
```bash
curl -s https://raw.githubusercontent.com/haveno-dex/haveno-pricenode/main/scripts/install_networksize_debian.sh | sudo bash
```

### Updating

Update your haveno code in /haveno/haveno with ```git pull```

Then build an updated pricenode:
```./gradlew :pricenode:installDist  -x test```

## How to deploy elsewhere

 - [docs/README-HEROKU.md](docs/README-HEROKU.md)
 - [docker/README.md](docker/README.md)
 