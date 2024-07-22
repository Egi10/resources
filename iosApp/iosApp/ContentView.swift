import SwiftUI
import proton

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        VStack {
            Text(greet)
            
            let resource = FeederRes.strings().tests
            Text(
                LocalizedStringKey(resource.resourceId),
                bundle: resource.bundle
            )
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
