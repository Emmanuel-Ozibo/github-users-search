# UI Architecture.

Jetpack compose is a reactive UI, this means that it only changes when the data(state) changes. On the other hand one of the major selling points of MVI is that the view(UI) needs to be dormant and only serves 2 purpose: 

- Render(Composition) and Re-render(Re-composition) the UI.
- Take user input(entering a text or clicking a button).

As you noticed, the philosophy of MVI matches perfectly with that of Jetpack compose or reactive UIs in general. 

### Concepts.

1. **MVI store**: This serves as the main driver of the whole MVI process, It connects actions to states and also triggers side effects. 
2. **Reducer**: This is responsible for converting actions into states. 
3. **Side effects**:  This is a function that doesn’t involve drawing the UI core business logic is implemented here. E.g Making an API call, reading form local storage etc…
4. **Action**: This is the main driver of the whole process it serves as the entry point to the MVI store. Actions are basically triggers, you can trigger an action that either only changes the UI or make a network request or do both. 

![MVI_ReadME.png](UI%20Architecture%203e34a5c7a9c1445fb2a12e2838b18951/MVI_ReadME.png)

### Module Architecture

Every screen gets a **state**(that is lifecycle aware) and dispatches an **action**(Intent). The state is responsible for delivering the UI data while the action is responsible for transmitting the user inputs. 

The action triggers the **MVI store** which then in turn calls the **reducer** to generate a new state based on the data provided by this action, when this is done the new state together with the action triggers the side effect(s), this then handles the business logic and calls either the API or the local storage to get or change data. The response form this side effect then triggers another action with the new data which then generates a new state based on the data in the response. This newly generated state is then transmitted back to the UI for re-composition. 

![Github_users_readme.png](UI%20Architecture%203e34a5c7a9c1445fb2a12e2838b18951/Github_users_readme.png)