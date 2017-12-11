
desc "Submit a new Beta build to google play"
lane :beta do

# perform a simple test
sh "yarn test"

gradle(task: "assembleRelease")

# Upload to beta
supply(track: 'beta')

end
