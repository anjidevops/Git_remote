
#/u/prog/wwrepos/assembla/tusharkapilaNeev/neevmoh/trunk/starter/src/main/scripts/deploy.sh
#../../../../
/apps/tomcats/a/bin/shutdown.sh
cp -R MohWeb/target/MohWeb/* /apps/tomcats/a/webapps/ROOT/
/apps/tomcats/a/bin/startup.sh